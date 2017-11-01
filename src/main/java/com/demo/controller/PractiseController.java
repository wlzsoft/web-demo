package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/practise")
public class PractiseController {
	
	@RequestMapping("/excercise")
	public String excercise(HttpServletRequest request ,HttpServletResponse response,String bookId,String chapterIds,String stata,RedirectAttributes attr){
		attr.addAttribute("bookId", bookId);
		attr.addAttribute("chapterIds", chapterIds);
		attr.addAttribute("stata", stata);
		if(null==stata||stata.equals("0")){//进入智能推荐算法
			return "redirect:/review/exRecommend.json";
	     }else if(stata.equals("0")){//进入 练新 算法
			 return "redirect:/review/exNew.json";
		}else if(stata.equals("1")){//进入 错题 算法
			return "redirect:/review/exError.json";
		}else if(stata.equals("2")){//进入巩固 算法
			return "redirect:/review/exStrengthen.json";
		}else{//进入智能推荐算法
			return "redirect:/review/exRecommend.json";
		}
	}

}
