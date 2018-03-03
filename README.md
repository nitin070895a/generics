# generics
Generic Classes / Activities / Fragments / Adapters

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
	        compile 'com.github.nitin070895a:generics:1.0'
	}
