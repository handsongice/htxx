package com.htxx.mapper;

import com.htxx.entity.Notice;
import com.htxx.entity.PageMsg;
import com.htxx.entity.YkfpDel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NoticeMapper {
    /**
     * 获取通知单列表 main
     *
     * @param pageMsg
     * @return
     */
    List<Notice> getNoticeList(PageMsg<Notice> pageMsg);

    /**
     * 通过ID 获取通知单列表 main
     *
     * @param tzdIds 通知单id数组
     * @return
     */
    List<Notice> getNoticeListById(String[] tzdIds);

    /**
     * 通过ID 删除通知单列表
     *
     * @param tzdIds 通知单id数组
     * @return
     */
    int deleteNotices(String[] tzdIds);

    /**
     * 通过查询条件 更新通知单提交状态
     *
     * @param pageMsg 查询条件
     * @return
     */
    int updateSubmitStatusByCondition(PageMsg<Notice> pageMsg);

    /**
     * 通过ID 更新通知单提交状态（已提交）
     *
     * @param tzdIds ID
     * @return
     */
    int updateSubmitStatusById(String[] tzdIds);

    /**
     * 通过gzdh工作单号 更新通知单状态（已开票）
     * @param gzdh 工作单号
     * @return
     */
    int updateInvoiceStatusByGzdh(String[] gzdh);
    /**
     * 插入一条通知单数据 main
     *
     * @param notice
     * @return
     */
    int insertNotice(Notice notice);

    /**
     * 插入一条通知单明细数据 del
     * @param noticeDel
     * @return
     */
    int insertNoticeDel(YkfpDel noticeDel);

    /**
     * 通过ID 更新通知单信息
     *
     * @param notice
     * @return
     */
    int updateNoticeById(Notice notice);

    /**
     * 通过工作单号获取通知单明细   main+detail
     * @param gzdh
     * @return
     */
    Notice getNoticeByGzdh(String gzdh);
}
