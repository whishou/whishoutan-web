package com.whishoutan.main.util;

import com.whishoutan.main.entity.Blog;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.ArrayList;
import java.util.List;

public class MarkdownToHTML {



    /**
     *
     *
     * */
    public static String markdownToHTML(String markdown)
    {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public static List<Blog> markdownToHTML(List<Blog> list)
    {
        Parser parser = Parser.builder().build();
        for (int i = 0; i < list.size(); i++) {
            Node document = parser.parse(list.get(i).getText());
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            renderer.render(document);
            list.get(i).setText(renderer.render(document));
        }
        return list;
    }
}
