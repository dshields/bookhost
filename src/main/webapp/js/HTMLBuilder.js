/*

Modified 7/24/2006 by Dorwin Shields for building HTML rather than DOM

Copyright (c) 2006 Dan Webb

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
IN THE SOFTWARE.
*/
HTMLBuilder = {
    apply : function(o) { 
      o = o || {};
        var els = ("p|div|span|strong|em|img|table|tr|td|th|thead|tbody|tfoot|pre|code|" + 
                       "h1|h2|h3|h4|h5|h6|ul|ol|li|form|input|textarea|legend|fieldset|" + 
                       "select|option|blockquote|cite|br|hr|dd|dl|dt|address|a|button|abbr|acronym|" +
                       "script|link|style|bdo|ins|del|object|param|col|colgroup|optgroup|caption|" + 
                       "label|dfn|kbd|samp|var").split("|");
    var el, i=0;
        while (el = els[i++]) o[el.toUpperCase()] = HTMLBuilder.tagFunc(el);
        return o;
    },
    tagFunc : function(tag) {
      return function() {
        var a = arguments, at, ch; a.slice = [].slice; if (a.length>0) { 
        if (a[0].nodeName || typeof a[0] == "string" || typeof a[0] == "number") ch = a;  else { at = a[0]; ch = a.slice(1); }
        if(dojo.lang.isArray(ch[0])) ch = ch[0];
        }
        return HTMLBuilder.elem(tag, at, ch);
      }
  },
    elem : function(e, a, c) {
        a = a || {}; c = c || [];
        var el = "<" + e + " ";
        for (var i in a) {
          if (typeof a[i] != 'function') {
            el = el + i + "=\"" + a[i] + "\" ";
          }
        }
        el = el + ">";
        for (var i=0; i<c.length; i++) {
                el = el + c[i];
        }
        el = el + "</" + e + ">"; 
        return el;
    }
};