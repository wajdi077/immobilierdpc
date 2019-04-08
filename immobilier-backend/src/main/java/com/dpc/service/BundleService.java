package com.dpc.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Bundle;
import com.dpc.domain.ValueHisto;
import com.dpc.repository.BundleRepository;
import com.dpc.repository.BundlesVersionRepository;
import com.dpc.repository.ValueHistoRepository;

@Service
public class BundleService {

    @Autowired
    private BundleRepository         bundles;
    @Autowired
    private ValueHistoRepository     valueRepo;
    @Autowired
    private BundlesVersionRepository bvRepo;

    private final Long               versionZero = Long.valueOf(0);
    static final int                 DEUX        = 2;
    static final int                 TRENTE      = 30;

    @SuppressWarnings("unchecked")
    public List<Bundle> getAllBundle(Long env) {
        return this.bundles.findByEnv(env);
    }

    public Bundle getBundleById(Long id) {
        return this.bundles.findOne(id);
    }

    public String createBundle(Bundle bundle, Long env) {

        try {
            bundle.setEnv(env);
            Bundle bundleExist = this.bundles.findByKeyAndEnv(bundle.getKey(), env);
            ValueHisto valueHisto = new ValueHisto(bundle.getValue(), bundle.getComment());
            Bundle bundleCreated = null;
            if (bundleExist != null) {
                bundle.setId(bundleExist.getId());
                valueHisto.setVersion(
                        this.valueRepo.findTopByBundleIdOrderByVersionDesc(bundleExist.getId()).getVersion() + 1);
                valueHisto.setBundle(bundleExist);
                this.valueRepo.save(valueHisto);
                this.bundles.save(bundle);
            } else {
                bundleCreated = this.bundles.save(bundle);
                valueHisto.setBundle(bundleCreated);
                valueHisto.setVersion(versionZero);
                this.valueRepo.save(valueHisto);
            }
            return "Success of creating bundle: " + bundle.getKey();
        } catch (RuntimeException e) {

            return "A problem occured during creation of the bundle: " + bundle.getKey();
        }

    }

    /*
     * @Override public Bundle updateBundle(Bundle bundle) { return
     * this.bundles.save(bundle); }
     */

    public void deleteBundle(String key, Long env) {
        Bundle bundle = this.bundles.findByKeyAndEnv(key, env);
        if (bundle != null) {
            this.bundles.delete(bundle.getId());
        }
    }

    public Collection<Bundle> getBundleByKey(String key, Long env) {
        return this.bundles.findByEnvAndKeyStartingWith(env, key);
    }

    public boolean bundleExist(String key, Long env) {
        return this.bundles.findByKeyAndEnv(key, env) != null;

    }

    public Workbook getLastBundlesVersionFile(Long env) throws IOException {

        Collection<Bundle> listBundle = this.bundles.findByEnv(env);
        Workbook workbook = new XSSFWorkbook();
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("listBundles");
        sheet.setDefaultColumnWidth(TRENTE);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Key");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Value");
        header.getCell(1).setCellStyle(style);

        header.createCell(DEUX).setCellValue("Comment");
        header.getCell(DEUX).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (Bundle aBundle : listBundle) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aBundle.getKey());
            aRow.createCell(1).setCellValue(aBundle.getValue());
            aRow.createCell(DEUX).setCellValue(aBundle.getComment());

        }

        return workbook;

    }

    public Workbook getPreviousBundlesVersionFile(Long env) throws IOException {

        Collection<Bundle> listBundle = this.bundles.findByEnv(env);

        // la dernière version depuis le tableau des fichiers
        Long lastVersion = this.bvRepo.findTopByEnvOrderByVersionDesc(env).getVersion();

        if (lastVersion > 0) {
            for (Bundle bundle : listBundle) {
                if (lastVersion
                        .equals(this.valueRepo.findTopByBundleIdOrderByVersionDesc(bundle.getId()).getVersion())) {
                    ValueHisto vh = this.valueRepo.findByBundleIdAndVersion(bundle.getId(), lastVersion - 1);
                    bundle.setValue(vh.getValue());
                    bundle.setComment(vh.getComment());
                }
            }
        }

        Workbook workbook = new XSSFWorkbook();
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("listBundles");
        sheet.setDefaultColumnWidth(TRENTE);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("Key");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Value");
        header.getCell(1).setCellStyle(style);

        header.createCell(DEUX).setCellValue("Comment");
        header.getCell(DEUX).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (Bundle aBundle : listBundle) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aBundle.getKey());
            aRow.createCell(1).setCellValue(aBundle.getValue());
            aRow.createCell(DEUX).setCellValue(aBundle.getComment());

        }

        return workbook;

    }

}
