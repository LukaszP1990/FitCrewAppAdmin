package com.fitcrew.FitCrewAppAdmin.validation;

import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.dto.validation.NotAllRequiredValueSetInAdminDto;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class NotAllRequiredValueSetInAdminDtoTest {

    private static AdminDto validAdminDto = AdminResourceMockUtil.adminDto();
    private static AdminDto notValidAdminDto = AdminResourceMockUtil.notValidAdminDto();

    @Mock
    private NotAllRequiredValueSetInAdminDto.NotAllRequiredValueSetInAdminDtoValidator adminValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    void setUp() {
        when(adminValidator.isValid(any(), any())).thenCallRealMethod();
    }

    @Test
    void shouldSucceedWhenRequiredValuesHaveBeenSet() {

        assertTrue(adminValidator
                .isValid(validAdminDto, constraintValidatorContext));
    }

    @Test
    void shouldFailWhenWhenRequiredValuesHaveNotBeenSet() {
        assertFalse(adminValidator
                .isValid(notValidAdminDto, constraintValidatorContext));
    }
}
