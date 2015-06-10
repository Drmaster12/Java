<%-- 
    Document   : index
    Created on : Oct 5, 2014, 5:19:31 PM
    Author     : gjung
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource var="affablebean" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost/affablebean"
                   user="root"  password="nbuser"/>
<sql:query var="categories" scope="session" dataSource="${affablebean}">    
    SELECT * FROM category
</sql:query>

            <div id="indexLeftColumn">
                <div id="welcomeText">
                    <p style="font-size: larger">Welcome to the online home of the Affable Bean Green Grocer.</p>

                    <p>Enjoy browsing and learning more about our unique home delivery
                        service bringing you fresh organic produce, dairy, meats, breads
                        and other delicious and healthy items to your doorstep.</p>
                </div>
            </div>

            <div id="indexRightColumn">
                <c:forEach var="category" items="${categories.rows}">
                    <div class="categoryBox">
                        <a href="category?${category.id}">
                            <span class="categoryLabel"></span>
                            <span class="categoryLabelText">${category.name}</span>

                            <img src="${initParam.categoryImagePath}${category.name}.jpg"
                                 alt="${category.name}" class="categoryImage">
                        </a>
                    </div>
                </c:forEach>
            </div>