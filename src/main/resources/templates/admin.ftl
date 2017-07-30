<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADMIN SWAG</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>


<#assign DATE = .now?date?iso_local>
<div class="form-style-5">
    <a name="top"></a>
    <form action="/adminapi/user/" method="POST" id="userCreate">
        <fieldset>
            <legend><span class="number">1</span> User Info</legend>
            <input type="text" name="username" placeholder="Username *" required pattern="[A-Za-z0-9]{1,30}" title=" A-Z or a-z or 0-9 ">
            <input type="text" name="email" placeholder="Email *" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,5}$">
            <input type="password" name="password" placeholder="Password *">
            <label for="fullname">Full Name</label>
            <input type="text" name="firstname" class="field-dividedl" placeholder="First *" required pattern="[A-Za-z0-9]{1,30}" title=" A-Z or a-z or 0-9 " />
            <input type="text" name="lastname" class="field-dividedr" placeholder="Last *" required pattern="[A-Za-z0-9]{1,30}" title=" A-Z or a-z or 0-9 " />
            <input type="date" name="birthday" value=${DATE} >
            <select id="party" name="party">
                <option value="tester">Tester</option>
                <option value="developer">Developer</option>
                <option value="manager">Manager</option>
                <option value="user" selected>User</option>
                <option value="admin" >Administrator</option>

            </select>
        </fieldset>
        <fieldset>
            <legend><span class="number">2</span> Additional Info</legend>
            <input type="text" name="zip" placeholder="Zip *"required>
            <input type="text" name="country" placeholder="Country *"required>
            <input type="text" name="city" placeholder="City *"required>
            <input type="text" name="district" placeholder="District *"required>
            <input type="text" name="street" placeholder="Street *"required>
            <input type="submit" id="userCreate" value="Create" />
            <input type="submit" id="userUpdate" value="Update" disabled />
        </fieldset>

    </form>
    <div class="wrap_search_bar">

        <form class="search_bar" action="/adminapi/search/" method="POST">
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

<#--
old version

<form class="search_bar" action="/api/search/" method="POST">
    <select id="by" name="by">
        <option value="email">Email</option>
        <option value="name" selected>User</option>
        <option value="birthday">Birthday</option>
    </select>

    <input type="text" name="subject" placeholder="Search for anything" />

    <button type="submit" value="Search">Search</button>
</form>-->



<#--Add User:
<form action="/api/user/" method="POST" id="addUser">
    Group: <input type="text" name="party" value="user"><br>
    Firstname: <input type="text" name="firstname" value="Anton"><br>
    Lastname: <input type="text" name="lastname" value="Anton"><br>
    Username: <input type="text" name="username" value="Anton"><br>
    Password: <input type="password" name="password" value="Anton"><br>
    Email: <input type="email" name="email" value="pro100sklm@ya.ru" placeholder="example@ya.ru"><br>
    <br><br><br>
    Birthday: <br>
    Is active?: <input type="checkbox" name="isActive" checked><br><br><br>
    Adress: <br><br>
    Zip: <input type="text" name="zip"><br>
    Country: <input type="text" name="zip"><br>
    City: <input type="text" name="zip"><br>
    District: <input type="text" name="zip"><br>
    Street: <input type="text" name="zip">
    <br>
    <input type="submit" value="Create">
</form>-->

<#--<#assign aDateTime = .now>
    <#assign aDate = aDateTime?date>
    <#assign aTime = aDateTime?time>
    Basic formats: <br>
    ${aDate?iso_utc} <br>
    ${aTime?iso_utc} <br>
    ${aDateTime?iso_utc} <br>
<br>
<br>
    Different accuracies: <br>
    ${aTime?iso_utc_ms} <br>
    ${aDateTime?iso_utc_m} <br>
<br>
<br>

    Local time zone: <br>
    ${aDateTime?iso_local} <br>

<br>
<br>
<br>-->
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
        <th>Edit</th>
        <th>Delete</th>
        <th>Active</th>
        <th>Zip</th>
        <th>Country</th>
        <th>City</th>
        <th>District</th>
        <th>Street</th>

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
        <#if (user.party == 'root') || !(user.active)>
            <td id="parent">
                <input type="submit"  value="Edit" class="edit" id=${user.id} disabled>
            </td>
            <td>
                <form action="/adminapi/delete/${user.id}" method="POST"  ><input type="submit" value="Delete" id=${user.id} disabled></input></form>
            </td>
            <td>
                <#if (user.active)>
                    <form action="/adminapi/change/${user.id}" method="POST"><input type="submit" value="Change" class="active" id=${user.id} disabled></input></form>
                <#else>
                    <form action="/adminapi/change/${user.id}" method="POST"><input type="submit" value="Change" class="inactive" id=${user.id}></input></form>
                </#if>
            </td>
        <#else>
            <td id="parent">
                <input type="submit"  onClick="scroll(0,0); return false" value="Edit" class="edit" id=${user.id}>
            </td>
            <td>
                <form action="/adminapi/delete/${user.id}" method="POST" ><input type="submit" value="Delete" id=${user.id}></input></form>
            </td>
            <td>
                <form action="/adminapi/change/${user.id}" method="POST"><input type="submit" value="Change" class="active" id=${user.id}></input></form>
            </td>
        </#if>
        <td id="zip">${user.address.zip}</td>
        <td id="country">${user.address.country}</td>
        <td id="city">${user.address.city}</td>
        <td id="district">${user.address.district}</td>
        <td id="street">${user.address.street}</td>
    </tr>
<#else>
    Sorry =) It is not your day!!! Here is no users by this request!
</#list>
    </tbody>
</table>
</div>
</body>

<script src="/js/main.js"></script>
<script src="/js/jquery-3.2.1.min.js"></script>


</html>