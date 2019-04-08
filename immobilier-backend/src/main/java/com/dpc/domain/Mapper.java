package com.dpc.domain;


public interface Mapper<U, V> {

    U fromDto(V dto);

    V toDto(U entity);
}
