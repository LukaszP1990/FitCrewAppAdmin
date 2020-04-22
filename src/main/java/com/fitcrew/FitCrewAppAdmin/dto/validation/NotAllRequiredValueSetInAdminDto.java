package com.fitcrew.FitCrewAppAdmin.dto.validation;

import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;

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
@Constraint(validatedBy = NotAllRequiredValueSetInAdminDto.NotAllRequiredValueSetInAdminDtoValidator.class)
public @interface NotAllRequiredValueSetInAdminDto {

    String message() default "Not all required values has been set in admin dto. This doesn't apply to the field adminId and encryptedPassword";

    Class<?>[] groups() default {};

    Class[] payload() default {};

    class NotAllRequiredValueSetInAdminDtoValidator implements ConstraintValidator<NotAllRequiredValueSetInAdminDto, AdminDto> {

        @Override
        public boolean isValid(AdminDto adminDto,
                               ConstraintValidatorContext constraintValidatorContext) {
            return checkAdmin(adminDto);
        }

        private boolean checkAdmin(AdminDto adminDto) {
            List<? extends Comparable<? extends Comparable<?>>> listOfFields = Stream.of(
                    adminDto.getFirstName(),
                    adminDto.getLastName(),
                    adminDto.getEmail(),
                    adminDto.getPassword())
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