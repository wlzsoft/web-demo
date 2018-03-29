package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ChapterDao;
import com.demo.dao.PointDao;
import com.demo.dto.IdEntity;
import com.demo.dto.PonitDto;
import com.pmp.entity.ChapterEntity;

@Service
public class ChapterService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired
	private PointDao pointDao;
	
	/**
	 * 新增章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@Transactional
	public IdEntity addChapter(ChapterEntity entity){
		Integer sort=entity.getSort();
		this.updateChapterSort(entity.getParentId(),entity.getBookId(),sort,1);
		entity.setCreateId(systemService.getCurrentUser().getId());
		entity.setCreateTime(new Date());
		entity.setSort(sort+1);
		chapterDao.addChapter(entity);
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());
	  return identity;
	}
	
	
	
	/**
	 * 
	 * 方法用途: 修改章节排序sort 号
	 * 操作步骤: TODO<br>
	 * @param chapterParentId 章节父Id
	 * @param bookId 练习本ID
	 * @param nextSort 新增|删除章节的节点id sort值
	 * @param flag 操作标示: 1新增  0删除 2编辑
	 * @return
	 */
	@Transactional
	public void updateChapterSort(Integer chapterParentId,Integer bookId ,Integer nextSort,Integer flag){
		List<ChapterEntity> list = chapterDao.findChapterSort(chapterParentId, bookId, nextSort);
			for(ChapterEntity entity :list){
				if(flag==1){
					entity.setSort(entity.getSort()+1);
					chapterDao.upateChapterSort(entity);
				}else if(flag==0){
					entity.setSort(entity.getSort()-1);
					chapterDao.upateChapterSort(entity);
				}else{
					
				}
			}
    }
	
	
	/**
	 * 修改章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public int editChapter(ChapterEntity entity,Integer oldSort){
		Integer sort=entity.getSort();
		if(oldSort!=entity.getSort()){
			this.updateChapterSort(entity.getParentId(),entity.getBookId(),sort,1);
		}
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		int count = chapterDao.editChapter(entity);
		return count;
	}
	
	/**
	 * 删除章节信息
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public int delChapter(String[] chapterIds,String bookId ){
		if(chapterIds.length>0){
			Integer[] chapterId = new Integer[chapterIds.length];
			for(int i=0;i<chapterIds.length;i++){
				chapterId[i]=Integer.parseInt(chapterIds[i]);
			}
			int count = chapterDao.delChapter(chapterId,Integer.parseInt(bookId));
			//List<ChapterEntity> list = chapterDao.findChapterById(chapterId);
			return count;
		}else{
			return 0;	
		}
	}
	
	
	/**
	 * 根据章节Id 练习本ID , 查询章节下面所有知识点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	public List<PonitDto> findChapterPoint(Integer chapterId,Integer bookId){
		return chapterDao.findChapterPoint(chapterId,bookId);
	
	}
	
	
	/**
	 * 根据练习本ID，查询练习本下面所有章节
	 * @param bookId
	 * @return
	 */
	public List<ChapterEntity> bookChapterList(Integer bookId){
		return chapterDao.bookChapterList(bookId);
	}
	
	
	/**
	 * 更改知识点的章节序号
	 * @param chapterSorts
	 */
	public void updateChapterSort(String[] chapterIds,Integer bookId){
		if(chapterIds.length>0){
			Integer[] chapterId = new Integer[chapterIds.length];
			for(int i=0;i<chapterIds.length;i++){
				chapterId[i]=Integer.parseInt(chapterIds[i]);
				pointDao.updatePointChapterSort(bookId, chapterId[i], i+1);
			}
			
			//批量处理上已经删除了的章节下的 知识点 章节序号
			pointDao.updateDelPointChapterSort(bookId, chapterId);
		}
	}
	
	
	/**
	 * 更改知识点的章节序号
	 * @param chapterSorts
	 */
	public void updateChapterSort(Integer bookId){
			//批量处理上已经删除了的章节下的 知识点 章节序号
			pointDao.updateDelPointChapterSortAll(bookId);
	}

}
