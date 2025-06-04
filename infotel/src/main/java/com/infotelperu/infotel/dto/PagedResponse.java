package com.infotelperu.infotel.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PagedResponse<T> {
    
    private List<T> items;
    private long total;
    private int pages;
    private int currentPage;
    
    // Constructores
    public PagedResponse() {}
    
    public PagedResponse(Page<T> page) {
        this.items = page.getContent();
        this.total = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.currentPage = page.getNumber() + 1; // Spring usa 0-based, API usa 1-based
    }
    
    public PagedResponse(List<T> items, long total, int pages, int currentPage) {
        this.items = items;
        this.total = total;
        this.pages = pages;
        this.currentPage = currentPage;
    }
    
    // Getters y Setters
    public List<T> getItems() { return items; }
    public void setItems(List<T> items) { this.items = items; }
    
    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }
    
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
    
    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }
}
