<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Hello There</title>
    <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>




    <link rel="stylesheet" href="/css/style.css">


</head>

<body>
<h1>Hello, ROI!</h1>

<div class="stand">
    <div class="outer-screen">
        <div class="inner-screen">
            <div class="form">
                <form action="/adminapi/signin" method="POST">
                <input name="name" type="text" class="zocial-dribbble" placeholder="Enter your username" required pattern="[a-z0-9]{1,30}" title=" a-z or 0-9 " />
                <input  name="password" type="password" placeholder="Password" />
                <input type="submit" value="Login" />
                </form>
                <a href="/signup">Don't have an accaunt? SIGN UP</a>
            </div>
        </div>
    </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>


</body>
</html>

