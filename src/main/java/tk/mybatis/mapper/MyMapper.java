package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author ASUS
 * @create 2019-08-09 21:43
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
