package com.spark.service.impl;

import com.spark.dao.ArticleDao;
import com.spark.domain.Article;
import com.spark.service.ArticleService;
import org.junit.Test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Wangzhuang2
 * @Project: Cyberspace
 * @Package com.spark.service.impl
 * @Description: TODO
 * @date Date : 2018-12-09  14:05
 * @version： V1.0
 */
public class ArticleServiceImplTest {

    private ArticleService articleService = new ArticleServiceImpl();


    /**
     * 统计实例化Service层 & Dao层 & Doamin层
     * */

    private Article article = new Article();

    @Test
    public void getHotInfoArticle(){
        try {
            List<Article> hotInfoArticle = articleService.getHotInfoArticle();
            if (hotInfoArticle != null){
                System.out.println("-----------> " + hotInfoArticle.size());
                System.out.println(hotInfoArticle);
            }else{
                System.out.println("测试失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insertIntoArticleReadCount(){
        article.setArticle_id(19);
        try {
            articleService.insertIntoArticleReadCount(article);
            System.out.println("------------------------> 阅读次数增加1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getDetailArticle(){
        article.setArticle_id(19);
        try {
            List<Article> articles = articleService.getDetailArticle(article);
            Article article = articles.get(0);
            System.out.println("article作者：" + article.getArticle_author());
            System.out.println("article发布日期：" + article.getArticle_time());
            System.out.println("article标题：" + article.getArticle_title());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("查询成功！............");
    }

    @Test
    public void findAllArticle() {
        article.setArticle_author("Java编程语言");
        try {
            List<Article> allArticle = articleService.findAllArticle(article);
            for(Article article : allArticle){
                System.out.println("--------> " + article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPageBean() {
    }

    @Test
    public void insertIntoArticle() {
        article.setArticle_author("Java编程语言");
        Date date_ = new Date(System.currentTimeMillis());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date_);
        article.setArticle_time(date);
        article.setArticle_title("论学习的重要性！");
        article.setArticle_desc("We are the world1");
        article.setArticle_sort("个人生活");
        article.setArticle_pic("images/uimg/1543992983.jpg");
        article.setIs_public(1); //公开
        article.setArticle_content("Navigate between editor and other tool windows\n" +
                "Description\n" +
                "\n" +
                "Action\n" +
                "\n" +
                "Switch schemes, keymaps, or view modes.\n" +
                "\n" +
                "In the Switch menu, select your option and press Enter. Use the same shortcut to undo your changes.\n" +
                "You can also find and adjust the color scheme settings in File | Settings/Preferences | Editor | Color Scheme and the keymap settings in File | Settings/Preference | Keymap.\tCtrl+`\n" +
                "Maximize editor pane.\n" +
                "\n" +
                "In this case IntelliJ IDEA hides all other windows so only the editor you currently work in is open.\tCtrl+Shift+F12\n" +
                "Switch the focus from other windows to an active editor.\n" +
                "\n" +
                "Escape\n" +
                "Return to an editor from the command-line terminal.\n" +
                "\n" +
                "However, note that in this case IntelliJ IDEA closes the terminal window.\n" +
                "To keep the terminal window open when you want to switch back to an active editor, press Ctrl+Tab.\n" +
                "\n" +
                "Alt+F12\n" +
                "Return to a default layout.\n" +
                "\n" +
                "In this case IntelliJ IDEA hides the Project tool window. However, you can select Window | Store Current Layout as Default from the main menu to save the current layout you are working in as default and use the same shortcut to restore it.\tShift+F12");
        try {
            articleService.insertIntoArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("article_author: " + article_author);
//        System.out.println("article_time: " + article_time);
//        System.out.println("article_title: " + article_title);
//        System.out.println("article_desc: " + article_desc);
//        System.out.println("article_sort: " + article_sort);
//        System.out.println("article_pic: " + article_pic);
//        System.out.println("article_public: " + article_public);
//        System.out.println("article_content: " + article_content);
    }
}