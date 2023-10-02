package fa.training.dao.converter;

import fa.training.dao.enums.EntryTestResults;

import javax.persistence.AttributeConverter;

public class EntryTestResultsConverter implements AttributeConverter<EntryTestResults,String> {
    @Override
    public String convertToDatabaseColumn(EntryTestResults entryTestResults) {
        switch (entryTestResults){
            case PASS:
                return "pass";
            case FAIL:
                return "fail";
            default:
                return null;
        }
    }

    @Override
    public EntryTestResults convertToEntityAttribute(String s) {
        switch (s){
            case "pass" :
                return EntryTestResults.PASS;
            case "fail":
                return EntryTestResults.FAIL;
            default:
                return null;
        }
    }
}
