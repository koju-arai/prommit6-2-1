$all(".data-refinement .switch").forEach((switchEl)=>{
  switchEl.addEventListener("click",(event)=>{
	
    const contentEl = switchEl.nextElementSibling;
	
	contentEl.classList.toggle("open");
	
	$all(".data-refinement .switch").forEach((el)=>{
	  const content = el.nextElementSibling;
	  
	  if(content !== contentEl){
		content.classList.remove("open")
	  };
	  if(content.classList.contains("open")){
        content.style.display = "block";	    
	  } else {
		content.style.display = "none";
	  };
    });
  });
});

$all(".data-refinement .cancel").forEach((switchEl)=>{
  
  switchEl.addEventListener("click",()=>{
	
	$all(".data-refinement .switch").forEach((el)=>{
	  const content = el.nextElementSibling;
	  content.classList.remove("open");
	  content.style.display = "none";
    });
	
  });
});

$all(".memo-menu div.switch").forEach((switchEl)=>{
  switchEl.addEventListener("click",(event)=>{
	
    const contentEl = switchEl.nextElementSibling;
	
	contentEl.classList.toggle("open");
	
	$all(".memo-menu div.switch").forEach((el)=>{
	  const content = el.nextElementSibling;
	  
	  if(content !== contentEl){
		content.classList.remove("open")
	  };
	  if(content.classList.contains("open")){
        content.style.display = "block";	    
	  } else {
		content.style.display = "none";
	  };
    });
  });
});

$all(".menu-box .switch").forEach((switchEl)=>{
  switchEl.addEventListener("click",(event)=>{
	
    const contentEl = $(".delete-dialog" ,switchEl.closest(".memo-menu"))
	
	contentEl.classList.toggle("open");
	if(contentEl.classList.contains("open")) $(".overlay").style.display = "block";
	
	$all(".delete-dialog").forEach((el)=>{
	  
	  if(el !== contentEl){
		el.classList.remove("open")
	  };
	  if(el.classList.contains("open")){
        el.style.display = "block";	    
	  } else {
		el.style.display = "none";
	  };
    });
  });
});

$all(".delete-dialog .delete-cancel").forEach((switchEl)=>{
  switchEl.addEventListener("click",(event)=>{
	
	$all(".delete-dialog").forEach((el)=>{
	  el.classList.remove("open");
	  el.style.display = "none";
	})
	$(".overlay").style.display = "none";
   
  });
});

function $(selectorText, parentElement = document){
    const el = parentElement?.querySelector(selectorText);
    return el;
};
function $all(selectorText, parentElement = document){
    const els = parentElement?.querySelectorAll(selectorText);
    return els;
};
function safeText(text, selectorText, parentElement = document){
    const el = $(selectorText, parentElement);
    if(el) el.textContent = text ?? "";
};