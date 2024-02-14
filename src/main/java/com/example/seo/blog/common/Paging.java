package com.example.seo.blog.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Paging<T> {

    private int totalCount;     // 전체 글 개수
    private int currentPage;    // 현재 페이지
    private int totalPages;     // 전체 페이지 개수
    private int startPage;      // 시작 페이지
    private int endPage;        // 끝 페이지
    private int pageCount;      // 화면 하단 노출 페이지 버튼 개수
    private int size;           // 페이지당 노출될 행 개수
    private List<T> content;

    public Paging(int totalCount, int currentPage, int pageCount, int size, List<T> content) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.content = content;
        this.size = size;
        this.pageCount = pageCount;

        if(totalCount == 0) {
            totalPages = 1;
            startPage = 1;
            endPage = 1;
        } else {
            totalPages = totalCount / size;
            if (totalCount % size > 0) {
                totalPages++;
            }
        }

        startPage = (currentPage - 1) / pageCount + 1;
        endPage = startPage + pageCount - 1;

        if(endPage > totalPages) {
            endPage = totalPages;
        }
    }
}
