package com.fyuizee.gamingapi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachedSlice<T> {

    private List<T> content;
    private boolean hasNext;

    public CachedSlice(Slice<T> slicedContent) {
        this.content = slicedContent.getContent();
        this.hasNext = slicedContent.hasNext();
    }

    public Slice<T> toSlice(Pageable pageable) {
        return new SliceImpl<>(this.content, pageable, this.hasNext);
    }

}
