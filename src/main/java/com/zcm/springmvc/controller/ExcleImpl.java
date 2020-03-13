package com.zcm.springmvc.controller;

import com.zcm.springmvc.dao.TUserMapper;
import com.zcm.springmvc.entity.PageBean;
import com.zcm.springmvc.entity.TUser;
import com.zcm.springmvc.entity.Tbdept;
import com.zcm.springmvc.entity.TbdeptEmpVo;
import com.zcm.springmvc.service.Impl.TbdeptServiceImpl;
import com.zcm.springmvc.service.TbdeptService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class ExcleImpl {
   @Autowired
   private  TbdeptService tbdeptService;
    public void export(String[] titles, ServletOutputStream out, HttpServletRequest request) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }

            // 第五步，写入实体数据
           // TbdeptServiceImpl tbdeptService=new TbdeptServiceImpl();
            PageBean<TbdeptEmpVo> pageBean=tbdeptService.pageAll(request);
            List<TbdeptEmpVo> list=pageBean.getLists();
           /* Person  person1=new Person("1","张三","123","26");
            Person  person2=new Person("2","李四","123","18");
            Person  person3=new Person("3","王五","123","77");
            Person  person4=new Person("4","徐小筱","123","1");

            //这里我把list当做数据库啦
            ArrayList<Person>  list=new ArrayList<Person>();
            list.add(person1);
            list.add(person2);
            list.add(person3);
            list.add(person4);*/

            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i+1);
                TbdeptEmpVo person = list.get(i);

                // 第六步，创建单元格，并设置值
                String  eid = null;
                if(person.getEid() != null){
                    eid = person.getEid().toString();
                }
                row.createCell(0).setCellValue(eid);
                String age = "";
                if(person.getAge() != null){
                    age = person.getAge().toString();
                }
                row.createCell(1).setCellValue(age);
                String gende = "";
                if(person.getGende() != null){
                    gende = person.getGende().toString();
                }
                row.createCell(2).setCellValue(gende);
                String workdate=null;
                if(person.getWorkdate() !=null){
                    workdate = person.getWorkdate().toString();
                }
                row.createCell(3).setCellValue(workdate);
                String password="";
                if(person.getPassword()!=null){
                  password=person.getPassword();
                }
                row.createCell(4).setCellValue(password);
                String ename="";
                if(person.getEname()!=null){
                    ename=person.getEname();
                }
                row.createCell(5).setCellValue(ename);
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");

        }
    }
}
