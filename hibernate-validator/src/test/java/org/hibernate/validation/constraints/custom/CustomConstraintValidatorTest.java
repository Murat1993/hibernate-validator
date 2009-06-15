// $Id$
/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validation.constraints.custom;

import java.util.Set;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import javax.validation.metadata.PropertyDescriptor;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * @author Emmanuel Bernard
 */

public class CustomConstraintValidatorTest {

	@Test
	public void testInheritedConstraintValidationImpl() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Phone p = new Phone();
		p.size = -2;
		final PropertyDescriptor propertyDescriptor = validator.getConstraintsForClass( Phone.class )
				.getConstraintsForProperty( "size" );
		assertNotNull( propertyDescriptor );
		final Set<ConstraintViolation<Phone>> constraintViolations = validator.validate( p );
		assertEquals( 1, constraintViolations.size() );
	}

	public static class Phone {
		@Positive public int size;
	}
}
