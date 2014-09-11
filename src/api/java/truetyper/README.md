An open TrueType Font renderer for Minecraft
 
USAGE GUIDE!

Create a TrueTypeFont object variable using either a resource location to a .ttf or
name of a system font, choose base size and whether or not you want anti aliasing like so:

testFont = FontLoader.createFont(new ResourceLocation("modid", "testfont.ttf"), 24f, false);

testFont2 = FontLoader.loadSystemFont("Arial", 16f, false);



Once the font has been created, to render to screen, simply use the static call:

FontHelper.drawString(String name, float x, float y, TrueTypeFont font, float scaleX, float scaleY, float... rgba)

eg, 
FontHelper.drawString(windowTitle, posX, posY, testFont, 1f, 1f);


Thanks to ProfMobius for tweaking the matrix ( http://www.twitter.com/ProfMobius )
 
 ----
 
 CHANGELOG:
 10/11/2013 - Added support for minecraft's colour code formatting ( § / \u00A7) - I personally use net.minecraft.util.EnumChatFormatting in my strings
  
 ----
 
TrueTyper in part, is modified from Slick2d, full details on their licensing can be found here:

http://slick.ninjacave.com/license/

 ----

TrueTyper: Open Source TTF implementation for Minecraft.
Copyright (C) 2013 - Mr_okushama ( http://www.twitter.com/Mr_okushama )

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.