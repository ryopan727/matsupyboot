package com.matsupy.cmn.mapper;

import java.util.List;

import com.matsupy.cmn.entity.SelectCondEntity;
import com.matsupy.cmn.entity.gen.UserTbl;

/**
 * UserTbl拡張Mapper
 */
public interface UserMapperExt {

	public UserTbl selUser(UserTbl entity);

	public List<UserTbl> selUserMulti();

	public List<UserTbl> selUserMultiLimit(UserTbl entity, SelectCondEntity selCondEntity);
}
