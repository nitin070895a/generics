# generics
Generic Classes / Activities / Fragments / Adapters

It has :

Basic Fragment with logs
Basic Activity with logs
Basic Fragment Activity

Recycler View Fragment
Web View Fragment
Web View Activity

Loading Fragment
Visible Loading Fragment
Tabbed Fragment

Collapsing Toolbar Activity
Navigation Drawer Activity
Intro screen activity
Snack bar and menu inflation integration in all activities.
About Activity

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

gradle :

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

dependencies {
	        compile 'com.github.nitin070895a:generics:1.2'
	}
