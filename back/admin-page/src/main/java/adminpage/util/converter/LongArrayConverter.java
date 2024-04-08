package adminpage.util.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


import java.util.Arrays;
import java.util.List;

@Converter
public class LongArrayConverter implements AttributeConverter<List<Long>, Long[]> {

    @Override
    public Long[] convertToDatabaseColumn(List<Long> attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toArray(new Long[0]);
    }

    @Override
    public List<Long> convertToEntityAttribute(Long[] dbData) {
        if (dbData == null) {
            return null;
        }
        return Arrays.asList(dbData);
    }
}
