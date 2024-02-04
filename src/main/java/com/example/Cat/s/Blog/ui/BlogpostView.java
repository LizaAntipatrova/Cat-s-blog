package com.example.Cat.s.Blog.ui;

import com.example.Cat.s.Blog.db.entity.posts.Blogpost;
import com.example.Cat.s.Blog.services.post.BlogpostService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@RolesAllowed(value = {"USER", "ADMIN"})
@Route(value = "/home", layout = MainLayout.class)
public class BlogpostView extends VerticalLayout {
    Grid<Blogpost> grid = new Grid<>(Blogpost.class);


    public BlogpostView(@Autowired
                        BlogpostService blogpostService) {
        addClassName("main-view");
        setSizeFull();
        configureGrid(blogpostService);
//        add(grid);
        VirtualList<Blogpost> list = new VirtualList<>();
        list.setItems(blogpostService.showAll());
        list.setRenderer(new ComponentRenderer<>(item -> {
            Div div = new Div();
            div.add(new H6(item.getTitle()));
            div.add(new Paragraph(item.getContent()));
            return div;
        }
        ));
        add(list);
    }


    private void configureGrid(BlogpostService blogpostService) {
        grid.addClassNames("post-grid");
        grid.setSizeFull();
//        grid.setColumns("id", "blogpostname", "blogpostRole");
        grid.setItems(blogpostService.showAll());
        grid.addColumn(Blogpost::getTitle).setHeader("Title");
        grid.addColumn(Blogpost::getAuthor).setHeader("Author");
        grid.addColumn(Blogpost::getPublicationDate).setHeader("Date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }

}