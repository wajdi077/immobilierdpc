package com.dpc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.common.DeviceProvider;
import com.dpc.domain.Utilisateur;
import com.dpc.domain.UtilisateurTokenState;
import com.dpc.repository.IAuthority;
import com.dpc.repository.UtilisateurRepository;
import com.dpc.repository.UtilsateurRepo;
import com.dpc.security.TokenHelper;
import com.dpc.security.Auth.JwtAuthenticationRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class AuthenticationController {

	@Autowired
	UtilisateurRepository userRepository;
	
    
    @Autowired 
    UtilsateurRepo utilsateurRepo;
    
    
    @Autowired
    IAuthority iAuthority;

	@Autowired
	TokenHelper tokenHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private DeviceProvider deviceProvider;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response, Device device) throws AuthenticationException, IOException {
System.out.println("bdiiiiiina***********");
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		System.out.println("bdiiiiiina******etape 1*****");
		// Inject into security context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("bdiiiiiina******etape 2*****"+authentication);
		// token creation
		Utilisateur user = (Utilisateur) authentication.getPrincipal();
		   Utilisateur u = new Utilisateur();
		   System.out.println("bdiiiiiina****etape 3*******");

		  u=  userRepository.findByUsername(authenticationRequest.getUsername());
		  System.out.println("bdiiiiiina****etape 4*******"+u.getUsername());

		  String profile = u.getProfil();
		String jws = tokenHelper.generateToken(user.getUsername(), device);
		  System.out.println("bdiiiiiina****etape 5*******"+jws);
		  System.out.println("bdiiiiiina****etape 5.1*******"+profile);
		int expiresIn = tokenHelper.getExpiredIn(device);
		expiresIn =1000000000;
		  System.out.println("bdiiiiiina****etape 6*******"+expiresIn);

		Long a = (long) expiresIn;
		  System.out.println("bdiiiiiina****etape 7*******"+a);

		// Add cookie to response
		//response.addCookie(createAuthCookie(jws, expiresIn));
		  System.out.println("bdiiiiiina****etape 8*******");

		// Return the token
		return ResponseEntity.ok(new UtilisateurTokenState(jws, a, profile,u.getUsername()));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		String authToken = tokenHelper.getToken(request);

		Device device = deviceProvider.getCurrentDevice(request);

		if (authToken != null && principal != null) {

			// TODO check user password last update
			String refreshedToken = tokenHelper.refreshToken(authToken, device);
			int expiresIn = tokenHelper.getExpiredIn(device);

			// Add cookie to response
			response.addCookie(createAuthCookie(refreshedToken, expiresIn));

			return ResponseEntity.ok(new UtilisateurTokenState(refreshedToken, expiresIn));
		} else {
			UtilisateurTokenState userTokenState = new UtilisateurTokenState();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}

	private Cookie createAuthCookie(String jwt, int expiresIn) {
		Cookie authCookie = new Cookie(tokenHelper.AUTH_COOKIE, (jwt));
		authCookie.setPath("/");
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(expiresIn);
		return authCookie;
	}
}