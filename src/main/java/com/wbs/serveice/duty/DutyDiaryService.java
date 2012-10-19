package com.wbs.serveice.duty;

import org.springframework.stereotype.Service;

import com.wbs.dao.duty.DutyDiaryDao;
import com.wbs.entity.duty.DutyDiary;
import com.wbs.serveice.StringIdEntityService;

/**
 * 值班日记Service
 */
@Service
public class DutyDiaryService extends
		StringIdEntityService<DutyDiary, DutyDiaryDao> {

}
