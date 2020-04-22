package com.fitcrew.FitCrewAppAdmin.validation;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.validation.NotAllRequiredValueSetInClientDto;
import com.fitcrew.FitCrewAppAdmin.util.ClientResourceMockUtil;
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
class NotAllRequiredValueSetInClientDtoTest {

    private static ClientDto validClientDto = ClientResourceMockUtil.clientDto();
    private static ClientDto notValidClientDto = ClientResourceMockUtil.notValidClientDto();

    @Mock
    private NotAllRequiredValueSetInClientDto.NotAllRequiredValueSetInClientDtoValidator clientValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach
    void setUp() {
        when(clientValidator.isValid(any(), any())).thenCallRealMethod();
    }

    @Test
    void shouldSucceedWhenRequiredValuesHaveBeenSet() {

        assertTrue(clientValidator
                .isValid(validClientDto, constraintValidatorContext));
    }

    @Test
    void shouldFailWhenWhenRequiredValuesHaveNotBeenSet() {
        assertFalse(clientValidator
                .isValid(notValidClientDto, constraintValidatorContext));
    }
}