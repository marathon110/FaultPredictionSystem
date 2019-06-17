<html>
<SCRIPT src="js/jquery.js"></SCRIPT>
<script type="text/javascript">

    window.setInterval(function(){
        caonima()
    }, 3000);


    function caonima(){
        $.ajax({
            type:"GET",
            url:"/predict_web2_war_exploded/stat",
            dataType:'json',
            async:false,
            success:function(result) {
              alert("ok")
            }
        })
    }
</script>
<body>
<h2>Hello World!</h2>
<input type="button" onclick="caonima()" value="button">
</body>
</html>
