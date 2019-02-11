package com.example.vo;

import java.util.List;

import com.example.demo.entity.StructureDetails;
import com.example.demo.entity.SweepFrequency;

public class StagingTableDataVO {

	private List<StructureDetails> structureDetailsVOs;
	private List<SweepFrequency> sweepFrequencyVOs;
	
	public List<StructureDetails> getStructureDetailsVOs() {
		return structureDetailsVOs;
	}
	public void setStructureDetailsVOs(List<StructureDetails> structureDetailsVOs) {
		this.structureDetailsVOs = structureDetailsVOs;
	}
	public List<SweepFrequency> getSweepFrequencyVOs() {
		return sweepFrequencyVOs;
	}
	public void setSweepFrequencyVOs(List<SweepFrequency> sweepFrequencyVOs) {
		this.sweepFrequencyVOs = sweepFrequencyVOs;
	}
	
}
