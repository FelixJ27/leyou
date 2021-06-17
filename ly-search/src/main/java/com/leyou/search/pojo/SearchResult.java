package com.leyou.search.pojo;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.TbBrand;

import com.leyou.item.pojo.TbCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Author: Felix
 * @Description:
 */
@Data
public class SearchResult extends PageResult<Goods> {

    private List<TbCategory> categories;

    private List<TbBrand> brands;

    private List<Map<String, Object>> specs;

    public SearchResult(Long total, Integer totalPage, List<Goods> items, List<TbCategory> categories, List<TbBrand> brands, List<Map<String, Object>> specs) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brands = brands;
        this.specs = specs;
    }


    public SearchResult() {
    }

    public List<TbCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<TbCategory> categories) {
        this.categories = categories;
    }

    public List<TbBrand> getBrands() {
        return brands;
    }

    public void setBrands(List<TbBrand> brands) {
        this.brands = brands;
    }
}
