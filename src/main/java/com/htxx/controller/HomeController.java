package com.htxx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @RequestMapping("sgtz")
    public ModelAndView sgtz() {
        ModelAndView mv = new ModelAndView("invoiceModule/sgtz");
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("sgsh")
    public ModelAndView sgsh() {
        ModelAndView mv = new ModelAndView("invoiceModule/sgsh");
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("dzkcgl")
    public ModelAndView dzkcgl() {
        ModelAndView mv = new ModelAndView("invoiceModule/dzkcgl");
        mv.addObject("kpjList", this.getCurrentKpjxx());
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("zzkcgl")
    public ModelAndView zzkcgl() {
        ModelAndView mv = new ModelAndView("invoiceModule/zzkcgl");
        mv.addObject("kpjList", this.getCurrentKpjxx());
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("fpcx")
    public ModelAndView fpcx() {
        ModelAndView mv = new ModelAndView("invoiceModule/fpcx");
        mv.addObject("kpjList", this.getCurrentKpjxx());
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("fppd")
    public ModelAndView fppd() {
        ModelAndView mv = new ModelAndView("invoiceModule/fppd");
        mv.addObject("kpjList", this.getCurrentKpjxx());
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("fpsj")
    public ModelAndView fpsj() {
        ModelAndView mv = new ModelAndView("invoiceModule/fpsj");
        mv.addObject("kpjList", this.getCurrentKpjxx());
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

    @RequestMapping("fpjx")
    public ModelAndView fpjx() {
        ModelAndView mv = new ModelAndView("invoiceModule/fpjx");
        mv.addObject("kpjList", this.getCurrentKpjxx());
        mv.addObject("fplxList", this.getCurrentFplx());
        return mv;
    }

}
