## Fonts in a True Type Collection Cannot Be Rendered Correctly



If you use `Font[] fnts = Font.loadFonts(getClass().getResourceAsStream(TEST_FONT), 0);`
A list of the font names and families are returned.  See below;

```bash
21:22:26.527 [JavaFX Application Thread] INFO  org.tjc.jfx.testingfonts.TestingFontsApp.start - 

Font[name=American Typewriter, family=American Typewriter, style=Regular, size=13.0],

Font[name=American Typewriter Bold, family=American Typewriter, style=Bold, size=13.0],

Font[name=American Typewriter Condensed Bold, family=American Typewriter, style=Condensed Bold, size=13.0],

Font[name=American Typewriter Light, family=American Typewriter, style=Light, size=13.0],

Font[name=American Typewriter Condensed, family=American Typewriter, style=Condensed, size=13.0],

Font[name=American Typewriter Condensed Light, family=American Typewriter, style=Condensed Light, size=13.0]


```

Because JavaFX doesn't return a proper font when you choose AmericanTypewr