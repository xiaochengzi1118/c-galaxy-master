package cn.tedu.galaxy.commons.restful;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JsonPage<T> implements Serializable {

    // 根据实际需求,定义需要的分页信息
    // 这里暂时只声明最基本的4个分页信息,如果有需求变化,随时添加即可
    @ApiModelProperty(value = "总页数",name="totalPages")
    private Integer totalPages;
    @ApiModelProperty(value = "总条数",name="totalCount")
    private Long totalCount;
    @ApiModelProperty(value = "当前页码",name="page")
    private Integer page;
    @ApiModelProperty(value = "每页条数",name="pageSize")
    private Integer pageSize;

    // JsonPage除了包含分页信息之外,也要包含查询到的数据
    @ApiModelProperty(value = "分页数据",name="list")
    private List<T> list;


    // 上面定义了所有分页需要的属性
    // 下面编写一个将PageInfo类型转换为JsonPage类型的方法
    // 如果需要转换别的类型(例如SpringData分页Page类型),编写一个新的方法即可
    public static <T> JsonPage<T>  restPage(PageInfo<T> pageInfo){
        // 所谓的转换就是将参数pageInfo中的信息,赋值到JsonPage对应的属性中
        JsonPage<T> result=new JsonPage<>();
        // 先赋值分页信息
        result.setTotalPages(pageInfo.getPages());
        result.setTotalCount(pageInfo.getTotal());
        result.setPage(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        // 再赋值分页数据
        result.setList(pageInfo.getList());
        // 最后返回赋完值的jsonPage对象
        return result;
    }

}
