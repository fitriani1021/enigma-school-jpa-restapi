package com.enigmacamp.model.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PagingResponse<T> extends CommonResponse{
    @Getter     @Setter
    private List<T> data;
    @Getter     @Setter
    private long count;
    @Getter     @Setter
    private int totalPages;
    @Getter     @Setter
    private int page;
    @Getter     @Setter
    private int pageSize;
    
    public PagingResponse(String message, Page<T> page) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = page.getContent();
        this.count = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber() + 1;
        this.pageSize = page.getSize();
    }
    
    @Override
    public String toString() {
        return "PagingResponse{" + "data=" + data + ", count=" + count + ", totalPages=" + totalPages + ", page=" + page + ", pageSize=" + pageSize + '}';
    }
}
