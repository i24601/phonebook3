package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
//앞부분의 주소를 따로 뺄수있다
public class PhoneController {
	//필드
	//생성자
	//get set
	//일반메소드
	
	@RequestMapping(value="/list",method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("/phone/list 확인");
		
		PhoneDao pDao = new PhoneDao();
		List<PersonVo> pList = pDao.getPersonList();
		
		System.out.println("받은 pList");
		System.out.println(pList.toString());
		
		model.addAttribute("pList",pList);
		System.out.println("모델에 pList add");
		
		return "/WEB-INF/view/list.jsp";		
		//포워딩을 하는게 아니라 서블릿이 포워딩하기 위해 주소값 전달
	}
	
	@RequestMapping(value="/updateForm",method={RequestMethod.GET,RequestMethod.POST})
	public String updateForm(@RequestParam("person_id") int person_id, Model model) {
		System.out.println("/phone/updateForm");//view를 보내는것 model도 보낼수있다 model&view
		System.out.println(person_id);
		PhoneDao pDao = new PhoneDao();
		PersonVo pVo = pDao.getPerson(person_id);
		System.out.println(pVo.toString());
		model.addAttribute("pVo",pVo);
		
		return "/WEB-INF/view/updateForm.jsp";
	}
	
	@RequestMapping(value="/writeForm",method={RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("/phone/writeForm");//view를 보내는것 model도 보낼수있다 model&view
		
		return "/WEB-INF/view/wform.jsp";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(@ModelAttribute PersonVo pVo) {
		System.out.println("/phone/update");//view를 보내는것 model도 보낼수있다 model&view
		PhoneDao pDao = new PhoneDao();
		System.out.println(pVo.toString());
		pDao.personUpdate(pVo);
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.GET,RequestMethod.POST})
	public String update(@RequestParam("person_id") int person_id) {
		System.out.println("/phone/delete");//view를 보내는것 model도 보낼수있다 model&view
		PhoneDao pDao = new PhoneDao();
		pDao.personDelete(person_id);
		return "redirect:/phone/list";
	}
	
	//파라미터로 받기(받아야할 파라미터가 적을때)
	
	/*
	 * @RequestMapping(value="/write",method=RequestMethod.GET) public String write(
	 * 
	 * @RequestParam("name") String name,
	 * 
	 * @RequestParam("hp") String hp,
	 * 
	 * @RequestParam(value="company", required=false, defaultValue="0000") String
	 * company) {
	 * 
	 * System.out.println("/phone/write 확인");
	 * System.out.println("파라미터 확인"+" 이름 "+name+" hp "+hp+" company "+company);
	 * PersonVo pVo = new PersonVo(name, hp, company); PhoneDao pDao = new
	 * PhoneDao(); pDao.personInsert(pVo); System.out.println("PersonDao-insert실행");
	 * return "/WEB-INF/view/list.jsp"; }
	 */
	 
	
	
	  //모델로 받기(받아야할 파라미터가 많을때)
	  @RequestMapping(value="/write",method=RequestMethod.GET) 
	  //모델로 받기위해서는 PersonVo에 setter(setter 이름 주의!!!!) 그리고 디폴트 생성자가 필요하다 (일단 디폴트 생성자를 돌린후 set해준다) 
	  public String write(@ModelAttribute PersonVo pVo){
	  System.out.println("/phone/write 확인"); 
	  System.out.println("모델확인");
	  System.out.println(pVo.toString()); 
	  PhoneDao pDao = new PhoneDao();
	  pDao.personInsert(pVo);
	  System.out.println("PersonDao-insert실행"); 
	  return "redirect:/phone/list";}
	
}
