package capstone.hospital.dto;

import lombok.Data;

@Data
public class Pagination {

    private int pageSize = 5;
    private int blockSize = 10;
    private int page = 1;
    private int block = 1;
    private int totalListCnt;
    private int totalPageCnt;
    private int totalBlockCnt;
    private int startPage = 1;
    private int endPage = 1;
    private int startIndex = 0;
    private int prevBlock;
    private int nextBlock;

    public Pagination(int totalListCnt, int page) {
        setPage(page);
        setTotalListCnt(totalListCnt);
        setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));
        setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));
        setBlock((int) Math.ceil((page * 1.0)/blockSize));
        setStartPage((block - 1) * blockSize + 1);
        setEndPage(startPage + blockSize - 1);
        if(endPage > totalPageCnt){this.endPage = totalPageCnt;}
        setPrevBlock((block * blockSize) - blockSize);
        if(prevBlock < 1) {this.prevBlock = 1;}
        setNextBlock((block * blockSize) + 1);
        if(nextBlock > totalPageCnt) {nextBlock = totalPageCnt;}
        setStartIndex((page-1) * pageSize);
        if (endPage == 0) {
            this.endPage = 1;
            this.nextBlock = 1;
            this.totalPageCnt = 1;
        }
    }
}
