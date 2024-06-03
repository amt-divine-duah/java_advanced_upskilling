package labs.lab_two.design_patterns.builder;

public class SearchQuery {
    private final String keywords;
    private final String dateRange;
    private final String category;
    private final int minPrice;
    private final int maxPrice;

    private SearchQuery(SearchQueryBuilder builder) {
        this.keywords = builder.keywords;
        this.dateRange = builder.dateRange;
        this.category = builder.category;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
    }

    // getters

    public String getKeywords() {
        return keywords;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getCategory() {
        return category;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public static class SearchQueryBuilder {
        private String keywords;
        private String dateRange;
        private String category;
        private int minPrice = 0;
        private int maxPrice = Integer.MAX_VALUE;

        public SearchQueryBuilder keywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        public SearchQueryBuilder dateRange(String dateRange) {
            this.dateRange = dateRange;
            return this;
        }

        public SearchQueryBuilder category(String category) {
            this.category = category;
            return this;
        }

        public SearchQueryBuilder minPrice(int minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public SearchQueryBuilder maxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }
        public SearchQuery build() {
            return new SearchQuery(this);
        }
    }

    public static void main(String[] args) {
        SearchQuery query = new SearchQuery.SearchQueryBuilder()
                .keywords("programming books")
                .category("Books")
                .dateRange("01-01-2020 to 12-31-2020")
                .minPrice(10)
                .maxPrice(50)
                .build();

        System.out.println("Search Keywords: " + query.getKeywords());
        System.out.println("Category: " + query.getCategory());
        System.out.println("Date Range: " + query.getDateRange());
        System.out.println("Price Range: " + query.getMinPrice() + " to " + query.getMaxPrice());
    }
}

