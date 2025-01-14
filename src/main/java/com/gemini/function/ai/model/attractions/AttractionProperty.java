package com.gemini.function.ai.model.attractions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class AttractionProperty {
    private String name;
    private String street;
    private String formatted;
    private String rating;
    private List<String> categories;
    private List<String> details;

    @Override
    public String toString() {
        return "AttractionProperty{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", address='" + formatted + '\'' +
                ", rating='" + rating + '\'' +
                ", categories=" + categories +
                ", details=" + details +
                '}';
    }
}
