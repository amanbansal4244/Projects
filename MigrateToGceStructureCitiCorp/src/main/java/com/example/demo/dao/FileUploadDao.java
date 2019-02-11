package com.example.demo.dao;


import com.example.vo.StagingTableDataVO;

public interface FileUploadDao {

	public void saveInputFileDataIntoStaggingTables(StagingTableDataVO stagingTableDataVO);
}
