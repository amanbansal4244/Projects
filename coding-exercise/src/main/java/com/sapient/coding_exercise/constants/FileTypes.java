/**
 * @author virjangi
 *
 */
package com.sapient.coding_exercise.constants;

public enum FileTypes {
	CSV_TYPE("csv",".csv"),
	TEXT_TYPE("txt",".txt");
	
	private String extension;
	private String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	FileTypes(String name, String extension) {
		this.extension = extension;
		this.name=name;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	
	
}
