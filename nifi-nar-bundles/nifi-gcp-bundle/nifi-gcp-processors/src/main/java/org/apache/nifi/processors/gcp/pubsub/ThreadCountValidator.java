/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nifi.processors.gcp.pubsub;

import org.apache.nifi.components.ValidationContext;
import org.apache.nifi.components.ValidationResult;
import org.apache.nifi.components.Validator;
import org.apache.nifi.processor.util.StandardValidators;
import java.util.Optional;

public class ThreadCountValidator implements Validator {

    @Override
    public ValidationResult validate(String subject, String input, ValidationContext context) {
        if (input == null || input.isEmpty() || "auto".equalsIgnoreCase(input)) {
            return new ValidationResult.Builder().subject(subject).input(input).valid(true).build();
        }
        // Use existing integer validator for the integer check
        return StandardValidators.POSITIVE_INTEGER_VALIDATOR.validate(subject, input, context);
    }
    
    public static Optional<Integer> parse(String input) {
        if (input == null || input.isEmpty() || "auto".equalsIgnoreCase(input)) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
