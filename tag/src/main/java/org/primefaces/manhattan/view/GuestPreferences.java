/*
   Copyright 2009-2021 PrimeTek.

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.primefaces.manhattan.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class GuestPreferences implements Serializable {

    private String theme = "teal-yellow";

    private String layoutMode = "layout-slim";

    private String menuColor = "light";

    private String inputStyle = "outlined";

    private List<Theme> themes;

    @PostConstruct
    public void init() {
        themes = new ArrayList<>();
        themes.add(new Theme("Blue-Grey", "blue-grey", "#3D72B4"));
        themes.add(new Theme("Blue Orange", "blue-orange", "#147df0"));
        themes.add(new Theme("Cyan Deep-Orange", "cyan-deeporange", "#00B4DB"));
        themes.add(new Theme("Dark-Pink Cyan", "darkpink-cyan", "#C06C84"));
        themes.add(new Theme("Deep-Purple Teal", "deeppurple-teal", "#5F2C82"));
        themes.add(new Theme("Green Orange", "green-orange", "#96C93D"));
        themes.add(new Theme("Green Pink", "green-pink", "#57CA85"));
        themes.add(new Theme("Green Purple", "green-purple", "#56AB2F"));
        themes.add(new Theme("Indigo Purple", "indigo-purple", "#4B79A1"));
        themes.add(new Theme("Indigo Yellow", "indigo-yellow", "#4E54C8"));
        themes.add(new Theme("Orange Cyan", "orange-cyan", "#e96443"));
        themes.add(new Theme("Orange Indigo", "orange-indigo", "#F3904F"));
        themes.add(new Theme("Pink Cyan", "pink-cyan", "#E94057"));
        themes.add(new Theme("Pink Teal", "pink-teal", "#d9427c"));
        themes.add(new Theme("Teal Yellow", "teal-yellow", "#136A8A"));
    }

    public String getTheme() {
		return theme;
	}
    
	public void setTheme(String theme) {
		this.theme = theme;
    }

    public String getLayoutMode() {
        return layoutMode;
    }

    public void setLayoutMode(String layoutMode) {
        this.layoutMode = layoutMode;
    }

    public String getMenuColor() {
        return menuColor;
    }

    public void setMenuColor(String menuColor) {
        this.menuColor = menuColor;
    }

    public String getInputStyleClass() {
        return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public class Theme {

        private String name;

        private String file;

        private String color;

        public Theme() {}

        public Theme(String name, String file, String color) {
            this.name = name;
            this.file = file;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
