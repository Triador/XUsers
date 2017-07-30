<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SIGN UP</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>


<#assign DATE = .now?date?iso_local>
<div class="form-style-5">
    <a name="top"></a>
    <form action="/signup" method="POST" id="userCreate">
        <fieldset>
            <legend><span class="number">1</span> User Info</legend>
            <input type="text" name="username" placeholder="Username *" required pattern="[A-Za-z0-9]{1,30}" title=" A-Z or a-z or 0-9 ">
            <input type="text" name="email" placeholder="Email *" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,5}$">
            <input type="password" name="password" placeholder="Password *">
            <label for="fullname">Full Name</label>
            <input type="text" name="firstname" class="field-dividedl" placeholder="First *" required pattern="[A-Za-z0-9]{1,30}" title=" A-Z or a-z or 0-9 " />
            <input type="text" name="lastname" class="field-dividedr" placeholder="Last *" required pattern="[A-Za-z0-9]{1,30}" title=" A-Z or a-z or 0-9 " />
            <input type="date" name="birthday" value=${DATE} >
            <select id="party" name="party" hide>
                <option value="tester">Tester</option>
                <option value="developer">Developer</option>
                <option value="manager">Manager</option>
                <option value="user" selected>User</option>
            </select>
        </fieldset>
        <fieldset>
            <legend><span class="number">2</span> Additional Info</legend>
            <input type="text" name="zip" placeholder="Zip *"required>
            <input type="text" name="country" placeholder="Country *"required>
            <input type="text" name="city" placeholder="City *"required>
            <input type="text" name="district" placeholder="District *"required>
            <input type="text" name="street" placeholder="Street *"required>
            <input type="submit" id="signUp" value="Sign Up" />

        </fieldset>

    </form>
</div>

</body>

<script src="/js/main.js"></script>
<script src="/js/jquery-3.2.1.min.js"></script>


</html>