package com.symbo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.symbo.dao.RoleDao;
import com.symbo.domain.security.Role;


/**
 * @author khaza.shaik
 *
 */
@SpringBootApplication
public class SymboBankAccount  implements CommandLineRunner {
	
	@Autowired
	RoleDao roleDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SymboBankAccount.class, args);
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role r =new Role();
		r.setRoleId(1);
		r.setName("ROLE_USER");
		
		Role r2 =new Role();
		r2.setRoleId(2);
		r2.setName("ROLE_ADMIN");
		
		roleDao.save(r);
		roleDao.save(r2);

		
	}
}
