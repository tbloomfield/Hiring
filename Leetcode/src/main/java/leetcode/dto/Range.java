package leetcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Range<T> {
    private T high;
    private T low;
}
