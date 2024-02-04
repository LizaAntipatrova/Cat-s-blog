package com.example.Cat.s.Blog.ui;

import com.example.Cat.s.Blog.db.entity.users.User;
import com.example.Cat.s.Blog.services.user.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@RolesAllowed(value = {"USER", "ADMIN"})
@Route(value = "/users", layout = MainLayout.class)
public class UserListView extends VerticalLayout {
    Grid<User> grid = new Grid<>(User.class);


    public UserListView(@Autowired
                        UserService userService) {
        addClassName("list-view");
        setSizeFull();
        configureGrid(userService);
        add(grid);
    }


    private void configureGrid(UserService userService) {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id", "username", "userRole");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        var users = userService.showAll();
        users.addAll(userService.showAll());
        grid.setItems(users);
    }

}