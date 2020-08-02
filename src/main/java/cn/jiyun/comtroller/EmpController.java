package cn.jiyun.comtroller;

import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Emp;
import cn.jiyun.service.DeptService;
import cn.jiyun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {
    @Value("${file.upload.path}")
    private String filePath;
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;
    @GetMapping("test")
    @ResponseBody
    public String test(){
        return "hello emp";
    }
    @GetMapping("toAddEmp")
    public String toAddEmp(Model model){
        List<Dept> depts = deptService.findAll();
        model.addAttribute("depts",depts);
        return "addEmp";
    }
    @PostMapping("addEmp")
    public String addEmp(@ModelAttribute(value="emp") Emp emp,@RequestParam(value="file")MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File photoFile = new File(filePath, fileName);
        if(!photoFile.getParentFile().exists()){
            photoFile.getParentFile().mkdirs();
        }
        file.transferTo(new File(filePath+File.separator+fileName));
        emp.setPhoto("/pic/"+fileName);
        empService.addEmp(emp);
        return "redirect:/emp/findAll";
    }
    @GetMapping("findAll")
    public String findAll(Model model){
        List<Emp> emps = empService.findAll();
        model.addAttribute("emps",emps);
        return "empList";
    }
    @GetMapping("delEmpById")
    public String deleteEmp(@RequestParam(value="eid")Integer eid){
        empService.delEmpById(eid);
        return "redirect:/emp/findAll";
    }
    @GetMapping("findEmpById")
    public String findEmpById(Model model,Integer eid){
        Emp empById = empService.findEmpById(eid);
        List<Dept> depts = deptService.findAll();
        model.addAttribute("depts",depts);
        model.addAttribute("empById",empById);
        return "updateEmp";
    }
    @PostMapping("updateEmp")
    public String updateEmp(@ModelAttribute(value="emp") Emp emp,@RequestParam(value="file")MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File photoFile = new File(filePath, fileName);
        if(!photoFile.getParentFile().exists()){
            photoFile.getParentFile().mkdirs();
        }
        file.transferTo(new File(filePath+File.separator+fileName));
        emp.setPhoto("/pic/"+fileName);
        empService.update(emp);
        return "redirect:/emp/findAll";
    }

}
