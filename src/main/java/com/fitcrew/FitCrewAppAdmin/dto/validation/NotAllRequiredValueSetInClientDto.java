package com.fitcrew.FitCrewAppAdmin.dto.validation;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = NotAllRequiredValueSetInClientDto.NotAllRequiredValueSetInClientDtoValidator.class)
public @interface NotAllRequiredValueSetInClientDto {

    String message() default "Not all required values has been set in client dto. This doesn't apply to the field clientId and encryptedPassword";

    Class<?>[] groups() default {};

    Class[] payload() default {};

    class NotAllRequiredValueSetInClientDtoValidator implements ConstraintValidator<NotAllRequiredValueSetInClientDto, ClientDto> {

        @Override
        public boolean isValid(ClientDto clientDto,
                               ConstraintValidatorContext constraintValidatorContext) {
            return checkClient(clientDto);
        }

        private boolean checkClient(ClientDto clientDto) {
            List<? extends Comparable<? extends Comparable<?>>> listOfFields = Stream.of(
                    clientDto.getFirstName(),
                    clientDto.getLastName(),
                    clientDto.getEmail(),
                    clientDto.getDateOfBirth(),
                    clientDto.getPhone())
                    .collect(Collectors.toList());
            return validateFields(listOfFields);
        }

        private boolean validateFields(List<? extends Comparable<? extends Comparable<?>>> listOfFields) {
            return Optional.ofNullable(listOfFields)
                    .map(field -> isField(listOfFields))
                    .orElse(false);
        }

        private boolean isField(List<? extends Comparable<? extends Comparable<?>>> listOfFields) {
            return IntStream.rangeClosed(0, listOfFields.size() - 1)
                    .allMatch(value -> Objects.nonNull(listOfFields.get(value)));
        }
    }
}