<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>USER SWAG</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>


<#assign DATE = .now?date?iso_local>
<div class="form-style-5">

    <div class="wrap_search_bar">

        <form class="search_bar" action="/userapi/search/" method="POST">
            <input type="text" name="subject" placeholder="Search for anything" />
            <select id="by" name="by">
                <option value="email">Email</option>
                <option value="name" selected>User</option>
                <option value="birthday">Birthday</option>
            </select>
            <input type="submit" value="Search"/>
        </form>
        <form action="/adminapi/signout/" method="POST" class="logout"><input type="submit" value="LOG OUT"></form>
    </div>
</div>
<div class="form-wrapper">
    <table class="zebra">
        <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Lastname</th>
            <th>Firstname</th>
            <th>Birthday</th>
            <th>Group</th>
            <th>Created</th>
            <th>Last Update</th>

        </tr>
        </thead>
        <tbody>


        <#list users as user>
        <tr>
            <td id="username" >${user.username}</td>
            <td id="email">${user.email}</td>
            <td id="lastname">${user.lastname}</td>
            <td id="firstname">${user.firstname}</td>
            <td id="birthday">${user.birthday?date?iso_local}</td>
            <td id="party">${user.party}</td>
            <td id="created">${user.createdTimestamp?date?iso_local} ${user.createdTimestamp?time?iso_local_nz}</td>
            <td id="updated">${user.lastUpdatedTimestamp?date?iso_local} ${user.lastUpdatedTimestamp?time?iso_local_nz}</td>
        </tr>
        <#else>
        Sorry =) It is not your day!!! Here is no users!
        </#list>
        </tbody>
    </table>
</div>
</body>

<script src="/js/main.js"></script>
<script src="/js/jquery-3.2.1.min.js"></script>


</html>