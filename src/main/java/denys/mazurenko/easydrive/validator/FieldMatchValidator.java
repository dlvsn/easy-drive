package denys.mazurenko.easydrive.validator;

import denys.mazurenko.easydrive.exception.PasswordValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value,
                           final ConstraintValidatorContext constraintValidatorContext) {
        boolean valid;
        try {
            final Object first = BeanUtils.getProperty(value, firstFieldName);
            final Object second = BeanUtils.getProperty(value, secondFieldName);
            valid = first == null && second == null || first != null && first.equals(second);
        } catch (Exception e) {
            throw new PasswordValidationException(
                    String.format("Password validation failed: %s", e.getMessage())
            );
        }
        return valid;
    }

}
