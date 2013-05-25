package org.homebudget.springmvc;

import info.joseluismartin.dao.Page;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

@SuppressWarnings("unchecked")
public class PaginatedListAdapter implements PaginatedList {

   private Page<?> model;

   public PaginatedListAdapter() {

      this(new Page(10));
   }

   public PaginatedListAdapter(Page<?> model) {

      this.model = model;
   }

   /**
    * {@inheritDoc}
    */
   public int getFullListSize() {

      return model.getCount();
   }

   /**
    * {@inheritDoc}
    */
   public List getList() {

      return model.getData();
   }

   /**
    * {@inheritDoc}
    */
   public int getObjectsPerPage() {

      return model.getPageSize();
   }

   /**
    * {@inheritDoc}
    */
   public int getPageNumber() {

      return model.getPage();
   }

   /**
    * {@inheritDoc}
    */
   public String getSearchId() {

      return null;
   }

   /**
    * {@inheritDoc}
    */
   public String getSortCriterion() {

      return model.getSortName();
   }

   /**
    * {@inheritDoc}
    */
   public SortOrderEnum getSortDirection() {

      return model.getOrder() == Page.Order.ASC ? SortOrderEnum.ASCENDING
            : SortOrderEnum.DESCENDING;
   }

   /**
    * @return the model
    */
   public Page<?> getModel() {

      return model;
   }

   /**
    * @param model
    *           the model to set
    */
   public void setModel(Page<?> model) {

      this.model = model;
   }

   public void setPage(int page) {

      model.setPage(page);
   }

   public void setSort(String sort) {

      model.setSortName(sort);
   }

   public void setDir(String dir) {

      model.setOrder("asc".equalsIgnoreCase(dir) ? Page.Order.ASC : Page.Order.DESC);
   }

}
