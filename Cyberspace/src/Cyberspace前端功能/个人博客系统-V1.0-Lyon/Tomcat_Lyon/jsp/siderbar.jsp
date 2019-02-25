<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
	  <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
		  <div class="sidebar-module sidebar-module-inset" style="font-family:Brush Script MT;color:#66CDAA;">
			  <h4 style="color:#8968CD;font-size:35px;font-family:monospace;">Thrivng</h4>
			  <p style="font-size:20px;font-family:fantasy;">《When you are old》</p>
				<p style="font-size:18px;font-family:Brush Script MT"><em>————William Butler Yeats. </em></p>
				<p style="font-size:15px;font-family:Vincent;font-weight:100">When you are old and grey and full of sleep,
					And nodding by the fire，take down this book,And slowly read,and dream of the soft look,
					Your eyes had once,and of their shadows deep;How many loved your moments of glad grace,
					And loved your beauty with love false or true, But one man loved the pilgrim Soul in you
					  And loved the sorrows of your changing face; And bending down beside the glowing bars,
						Murmur,a little sadly,how Love fled And paced upon the mountains overhead And hid his face amid a crowd of stars.
				</p>
		  </div>
      <div class="sidebar-module">
        <h4 style="font-size:35px;color:#9F79EE;"><strong>Tags</strong></h4>
          <ol class="list-unstyled">
						<c:forEach items="${allTags}" var="tag">
							<li style="font-size:18px;margin:8px;"><a href="/Lyon/foregetTagarticles?tagName=${tag}" target="_blank"><span style="color:#98F5FF;" class="label label-default">${tag}</span></a></li>
						</c:forEach>
          </ol>
      </div>
      <div class="sidebar-module">
          <h4 style="font-size:35px;color:#9F79EE;"><strong>Other</strong></h4>
          <ol class="list-unstyled" >
						<li style="margin:10px;"><a href="https://www.zhihu.com/people/better-man007/activities">知乎-Lyon</a></li>
            <li style="margin:10px;"><a href="#">微信公众号-阳光流淌007</a></li>
						<li style="margin:10px;"><a href="https://github.com/Flowingsun007">GitHub-Flowingsun007</a></li>
          </ol>
		  </div>
    </div><!-- /.blog-sidebar -->
  </div><!-- /.row -->
</div><!-- /.container -->
