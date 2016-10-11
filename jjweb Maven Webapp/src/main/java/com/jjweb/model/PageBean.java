package com.jjweb.model;

import java.util.List;

public class PageBean {
 
private int allRow; //�ܼ�¼��
private int currentPage; //��ǰҳ
private boolean firstPage; //��ҳ
private boolean hasNextPage; //��һҳ
private boolean hasPreviousPage; //ǰһҳ
private boolean lastPage; //βҳ

private List list; //Ҫ���ص�ĳһҳ�ļ�¼�б�
private int offset; //��ǰҳ��ʼ��¼
private int pageSize; //ÿҳ��¼��
private int totalPage; //��ҳ��
 
 
public int getAllRow() {
return allRow;
}
 
public int getCurrentPage() {
return currentPage;
}
 
public List getList() {
return list;
}
 
 
public int getOffset() {
return offset;
}
 
public int getPageSize() {
return pageSize;
}
 
public int getTotalPage() {
return totalPage;
}
 
public boolean isFirstPage() {
return firstPage;
}
 
public boolean isHasNextPage() {
return hasNextPage;
}
 
public boolean isHasPreviousPage() {
return hasPreviousPage;
}
 
public boolean isLastPage() {
return lastPage;
}
 
public void setAllRow(int allRow) {
this.allRow = allRow;
}
 
 
public void setCurrentPage(int currentPage) {
this.currentPage = currentPage;
}
 
public void setFirstPage(boolean firstPage) {
this.firstPage = firstPage;
}
 
public void setHasNextPage(boolean hasNextPage) {
this.hasNextPage = hasNextPage;
}
 
public void setHasPreviousPage(boolean hasPreviousPage) {
this.hasPreviousPage = hasPreviousPage;
}
 
public void setLastPage(boolean lastPage) {
this.lastPage = lastPage;
}
 
public void setList(List list) {
this.list = list;
}
 
public void setOffset(int offset) {
this.offset = offset;
}
 
public void setPageSize(int pageSize) {
this.pageSize = pageSize;
}
public void setTotalPage(int totalPage) {
this.totalPage = totalPage;
}
 
 
 
public static int countCurrentPage(int page){
final int curPage = (page==0?1:page);
return curPage;
}
 
public static int countOffset(final int pageSize,final int currentPage){
final int offset = pageSize*(currentPage-1);
return offset;
}
 
public static int countTotalPage(final int pageSize,final int allRow){
int totalPage = (allRow % pageSize == 0) ? (allRow/pageSize) : (allRow/pageSize+1);
System.out.println("totalpage = " + totalPage);
return totalPage;
}
}