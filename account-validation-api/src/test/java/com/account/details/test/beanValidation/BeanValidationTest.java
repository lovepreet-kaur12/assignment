package com.account.details.test.beanValidation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.account.details.model.UserResponse;
import com.account.details.model.User;
import com.account.details.service.UserServiceImpl;

@SpringBootTest
public class BeanValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    
    @Test
    public void shouldHaveNoViolations() {
        
    	 User user = new User();
         user.setName("LoveUser");
 		 user.setAccNo(87654321);
 		 user.setSrcCode(121212);
 		 user.setPanNumber("7458934736219124");
 
        
        Set<ConstraintViolation<User>> violations
                = validator.validate(user);
 
        
        assertTrue(violations.isEmpty());
    }
    @Test
    public void testPanLength() {

        User user = new User();
        user.setName("LoveUser");
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
        ConstraintViolation<User> violation=violations.iterator().next();
        assertEquals("PAN must consist of all digits and should be of length 16",violation.getMessage());

    }
    
    @Test
    public void isPanNumberNumeric() {

        User user = new User();
        user.setName("TestUser");
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219RYU");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
        ConstraintViolation<User> violation=violations.iterator().next();
        assertEquals("PAN must consist of all digits and should be of length 16",violation.getMessage());

    }
    
    
    @Test
    public void isPanNumberPositive() {

        User user = new User();
        user.setName("TestUser");
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("-7458934736219RYU");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
        ConstraintViolation<User> violation=violations.iterator().next();
        assertEquals("PAN must consist of all digits and should be of length 16",violation.getMessage());

    }
    
    
    @Test
    public void isNameNull() {

        User user = new User();
        user.setName(null);
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219123");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        
        ConstraintViolation<User> violation=violations.iterator().next();
        assertEquals("Name should not be null",violation.getMessage());

    }
    
 
}
